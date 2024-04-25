package es.uoc.icl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Jwt {
	private String token;
}
