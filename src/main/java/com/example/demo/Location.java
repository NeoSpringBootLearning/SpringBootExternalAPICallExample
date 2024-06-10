package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

	private String name;
	private String region;
	private String country;
	private float lat;
	private float lon;
	private String tz_id;
	private long localtime_epoch;
	private String localtime;
}
