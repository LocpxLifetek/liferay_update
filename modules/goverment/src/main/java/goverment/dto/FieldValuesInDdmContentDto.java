package goverment.dto;

public class FieldValuesInDdmContentDto {
	private String instanceId;
	private String name;
	private String fieldReference;
	private ValueDlFileDto value;
	
	
	
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFieldReference() {
		return fieldReference;
	}
	public void setFieldReference(String fieldReference) {
		this.fieldReference = fieldReference;
	}
	
	public ValueDlFileDto getValue() {
		return value;
	}
	public void setValue(ValueDlFileDto value) {
		this.value = value;
	}
	public FieldValuesInDdmContentDto() {
		super();
	}
	
	
}
