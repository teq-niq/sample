package sample.model;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@XmlRootElement(name = "employee")

@XmlAccessorType(XmlAccessType.FIELD)
public class Employee extends Person {
@NotNull
private String login;
}

