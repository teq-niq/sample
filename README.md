This may not be the best way to achieve this.  
It  demonstrates how  io.swagger.v3.core.jackson.SchemaSerializer can be extended and replaced with whatever works for the user.  
This can also help get more community driven extensions of io.swagger.v3.core.jackson.SchemaSerializer and cause improvement in io.swagger.v3.core.jackson.SchemaSerializer

Since swagger code must be changed for this best to implement this as a proper PR and not this way. 

Do wonder if there is a better way of achieving this indirection.

See input.txt for a sample json input for the request.