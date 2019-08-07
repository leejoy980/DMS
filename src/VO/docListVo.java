package VO;

public class docListVo {
	private String user_id;
	private String listType;
	private int doc_id;
	private String state;
	private String title;
	private String gen_user_id;
	private String created_time;
	private String doc_Type;
	private String gen_user_name;
	
	
	public docListVo(String user_id,String listType,int doc_id,String state,String title,String gen_user_id,String created_time
			,String doc_Type,String gen_user_name){
		
		this.user_id=user_id;
		this.listType=listType;
		this.doc_id=doc_id;
		this.state=state;
		this.title=title;
		this.gen_user_id=gen_user_id;
		this.created_time=created_time;
		this.doc_Type=doc_Type;
		this.gen_user_name=gen_user_name;
		
	}
	
	public String getUser_id(){
		return user_id;
	}
	public String getListType(){
		return listType;
	}
	public int getDoc_id(){
		return doc_id;
	}
	public String getState(){
		return state;
	}
	public String getTitle(){
		return title;
	}
	public String getGen_user_id(){
		return gen_user_id;
	}
	public String getCreated_time(){
		return created_time;
	}
	public String getDoc_Type(){
		return doc_Type;
	}
	public String getGen_user_name(){
		return gen_user_name;
	}
	
	
	
	public void setUser_id(String user_id){
		this.user_id=user_id;
	}
	public void setListType(String listType){
		this.listType=listType;
	}
	public void setDoc_id(int doc_id){
		this.doc_id=doc_id;
	}
	public void setState(String state){
		this.state=state;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public void setGen_user_id(String gen_user_id){
		this.gen_user_id=gen_user_id;
	}
	public void setCreated_time(String created_time){
		this.created_time=created_time;
	}
	public void setDoc_Type(String doc_Type){
		this.doc_Type=doc_Type;
	}
	public void setGen_user_name(String gen_user_name){
		this.gen_user_name=gen_user_name;
	}

	
}
