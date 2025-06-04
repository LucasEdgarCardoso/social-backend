package com.nulo.social.model.user;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class UserEntity {

	@Id private ObjectId id;
	@NonNull private String name;
	
	@NonNull @Indexed(unique = true)
	private String email;
	
	private String bio;
	private Boolean deleted = false;

}
