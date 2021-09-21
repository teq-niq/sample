This may not be the best way to achieve this.  
Its definitely a hack.  
However it does demonstrates a  need to be able to extend io.swagger.v3.core.jackson.SchemaSerializer and replace it with whatever works for the user.  
This can also help get more community driven extensions of io.swagger.v3.core.jackson.SchemaSerializer and cause improvement in io.swagger.v3.core.jackson.SchemaSerializer

The pro for this hack is that no swagger code needs changing.  
The con is that SchemaSerializerReplacer.java must be created in package : com.fasterxml.jackson.databind.cfg

See input.txt for a sample json input for the request.