package sample.config;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.swagger.v3.core.jackson.SchemaSerializer;
import io.swagger.v3.oas.models.media.Schema;

public class CustomSerializer extends SchemaSerializer {

	//no harm done here and in super both refer to same object 
	private JsonSerializer<Object> defaultSerializer;
	 
	public CustomSerializer(JsonSerializer<Object> serializer) {
		super(serializer);
		defaultSerializer = serializer;
		
	}
	
	@Override
    public void serialize(
            Schema value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        // handle ref schema serialization skipping all other props
        if (StringUtils.isBlank(value.get$ref())) {
            defaultSerializer.serialize(value, jgen, provider);
        } else {
            jgen.writeStartObject();
            jgen.writeStringField("$ref", value.get$ref());
            String ref = value.get$ref();
            if(!ref.startsWith("#/components/schemas/"))
            {
            	ref="#/components/schemas/"+ref;
            	value.$ref(ref);
            }
           
            //copyExtensions(value, jgen);
            jgen.writeEndObject();
        }
    }
    //copy extensions
	//related to 
	//https://github.com/swagger-api/swagger-core/pull/3539
	//Complex types are handled by ref
	//It would be convenient in spite of their being ref to show 
	//constraints by schema extensions

    protected void copyExtensions(Schema value, JsonGenerator jgen) throws IOException {
        Map<String, Object> extensions = value.getExtensions();
        if(extensions!=null) {
            Set<String> extensionsKeySet = extensions.keySet();
            for (String extensionKey : extensionsKeySet) {
                Object extensionValue = extensions.get(extensionKey);
                if(extensionValue!=null) {
                   jgen.writeObjectField(extensionKey, extensionValue);
                } else {
                    jgen.writeNullField(extensionKey);
                }
            }

        }
    }

}
