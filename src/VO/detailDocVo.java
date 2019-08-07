package VO;

import java.util.Map;

public class detailDocVo {
	private int doc_id;
	private String doc_Type;
	private String title;
	private String content;
	private String created_time;
	private String attached;
	private String gen_user_id;
	private String des_1;
	private String des_2;
	private String des_3;
	private String state;
	private String gen_user_name;
	private String des_1_name;
	
	public detailDocVo(String doc_Type,String title,String content,
			String attached,String gen_user_id,String des_1){
		this.doc_id=0;
		this.doc_Type=doc_Type;
		this.title=title;
		this.content=content;
		this.attached=attached;
		this.gen_user_id=gen_user_id;
		this.des_1=des_1;
		this.des_2=null;
		this.des_3=null;
		this.state=null;
	}
	public detailDocVo(String doc_Type,String title,String content,
			String attached,String gen_user_id,String des_1,String des_2,String des_3){
		this.doc_id=0;
		this.doc_Type=doc_Type;
		this.title=title;
		this.content=content;
		this.attached=attached;
		this.gen_user_id=gen_user_id;
		this.des_1=des_1;
		this.des_2=des_2;
		this.des_3=des_3;
		this.state="first";
	}
	public detailDocVo(int doc_id,String title,String content,String attached){
		this.doc_id = doc_id;
		this.title = title;
		this.attached = attached;
		this.content = content;
	}
	public int getDoc_id(){
		return doc_id;
	}
	public String getDoc_Type(){
		return doc_Type;
	}
	public String getTitle(){
		return title;
	}
	public String getContent(){
		return content;
	}
	public String getCreated_time(){
		return created_time;
	}
	public String getAttached(){
		return attached;
	}
	public String getGen_user_id(){
		return gen_user_id;
	}
	public String getDes_1(){
		return des_1;
	}
	public String getDes_2(){
		return des_2;
	}
	public String getDes_3(){
		return des_3;
	}
	public String getState(){
		return state;
	}
	public String getGen_user_name(){
		return gen_user_name;
	}
	public String getDes_1_name(){
		return des_1_name;
	}
	
	
	public void setDoc_id(int doc_id){
		this.doc_id=doc_id;
	}
	public void setDoc_Type(String doc_Type){
		this.doc_Type=doc_Type;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public void setContent(String content){
		this.content=content;
	}
	public void setCreated_time(String created_time){
		this.created_time=created_time;
	}
	public void setAttached(String attached){
		this.attached=attached;
	}
	public void setGen_user_id(String gen_user_id){
		this.gen_user_id=gen_user_id;
	}
	public void setDes_1(String des_1){
		this.des_1=des_1;
	}
	public void setDes_2(String des_2){
		this.des_2=des_2;
	}
	public void setDes_3(String des_3){
		this.des_3=des_3;
	}
	public void setState(String state){
		this.state=state;
	}
	public void setGen_user_name(String gen_user_name){
		this.gen_user_name=gen_user_name;
	}
	public void setDes_1_name(String des_1_name){
		this.des_1_name=des_1_name;
	}
	
	public void validate(Map<String,Boolean> errors){
		if ( title == null || title.trim().isEmpty()){
			errors.put("title", Boolean.TRUE);
		}
	}
}
