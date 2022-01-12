package goverment.dto;

import java.util.ArrayList;
import java.util.List;

public class DlFileEntryVideoDto {
	private List availableLanguageIds;
	private String defaultLanguageId;
	private List<FieldValuesInDdmContentDto> fieldValues =new ArrayList<>();
	public List getAvailableLanguageIds() {
		return availableLanguageIds;
	}
	public void setAvailableLanguageIds(List availableLanguageIds) {
		this.availableLanguageIds = availableLanguageIds;
	}
	public String getDefaultLanguageId() {
		return defaultLanguageId;
	}
	public void setDefaultLanguageId(String defaultLanguageId) {
		this.defaultLanguageId = defaultLanguageId;
	}
	public List<FieldValuesInDdmContentDto> getFieldValues() {
		return fieldValues;
	}
	public void setFieldValues(List<FieldValuesInDdmContentDto> fieldValues) {
		this.fieldValues = fieldValues;
	}
	public DlFileEntryVideoDto() {
		super();
	}
	

	
	
}