package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import io.swagger.v3.oas.models.media.Schema;
import sample.config.CustomSerializer;

public class SchemaSerializerReplacer {
	
	public static void modify(SerializerFactoryConfig factoryConfig )
	{
		BeanSerializerModifier[] original = factoryConfig._modifiers;
		if(original.length>0)
		{
			
			for (int i = 0; i < original.length; i++) {
				BeanSerializerModifier beanSerializerModifier=original[i];
			

				Class<? extends BeanSerializerModifier> class1 = beanSerializerModifier.getClass();
				
				Class<?> enclosingClass = class1.getEnclosingClass();
				if(enclosingClass!=null)
				{
					Class<?> enclosingClass2 = enclosingClass.getEnclosingClass();
					if(enclosingClass2!=null && enclosingClass2==io.swagger.v3.core.util.ObjectMapperFactory.class)
					{
						original[i]=new BeanSerializerModifier() {
		                    @Override
		                    public JsonSerializer<?> modifySerializer(
		                            SerializationConfig config, BeanDescription desc, JsonSerializer<?> serializer) {
		                        if (Schema.class.isAssignableFrom(desc.getBeanClass())) {
		                            return new CustomSerializer((JsonSerializer<Object>) serializer);
		                        }
		                        return serializer;
		                    }
		                };
					}
				}
				
				
				
				
				
			}
			
		}
	}

}
