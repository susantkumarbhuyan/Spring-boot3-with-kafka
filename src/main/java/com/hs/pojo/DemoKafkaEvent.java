package com.hs.pojo;

public class DemoKafkaEvent {
	private String name;
	private long id;
	private String details;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "DemoKafkaEvent [name=" + name + ", id=" + id + ", details=" + details + "]";
	}

}
