package com.yan.redisson.schema;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{

	public String id;
	
	public String name;
	
	public String email;
}
