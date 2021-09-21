package sample.config;

import java.io.IOException;

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
            jgen.writeEndObject();
        }
    }
 

}
