package models;

import lombok.Data;

@Data
public class User {
	public Gift gift;
	public boolean hasGift = gift != null;
}
