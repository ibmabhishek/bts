package com.ibm.bts.entity;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Bug {
	@Id
	private String id;
	@NotNull
	@NotBlank
	private String description;
	@NotNull
	@NotBlank
	private String status;
	private Date createdDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		String[] statusArray = {"NEW","URGENT","VERIFIED","NEED_MORE_INFO","CLOSED"};
		int count = 0;
		for (int i = 0; i < statusArray.length; i++) {
			if(status.equals(statusArray[i])) {
				count++;
			}
		}
		
		if(count == 0) {
			throw new IllegalArgumentException("Invalid status");
		}
		
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
