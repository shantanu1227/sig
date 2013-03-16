package com.android.personalca;

public class AccountDetails {

		int _id;
		String name;
		int amount;
		
		public AccountDetails(){
			
					}
		public AccountDetails(int id, String name, int amount ){
			this._id=id;
			this.name=name;
			this.amount=amount;
			
		}
		public AccountDetails(String name, int amount ){
			this.name=name;
			this.amount=amount;
		}
		public int getID(){
			return this._id;
						
		}
		public void setID(int id){
			this._id =id;
			
		}
		public String getName(){
			return this.name;
		}
		public void setName(String name){
			this.name=name;
					}
		public int getAmount(){
			return this.amount;
			
		}
		public void setAmount(int amount){
			this.amount=amount;
			
		}
		
}
