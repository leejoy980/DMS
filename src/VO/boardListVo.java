package VO;

public class boardListVo {
	private String boardType;
	private int doc_id;
	private String title;
	private String gen_user_id;
	private String created_time;
	private String gen_user_name;
	private String answer;
	
	public boardListVo(String boardType,int doc_id,String title,String gen_user_id,String created_time
			,String gen_user_name,String answer){
		
		this.boardType=boardType;
		this.doc_id=doc_id;
		this.title=title;
		this.gen_user_id=gen_user_id;
		this.created_time=created_time;
		this.gen_user_name=gen_user_name;
		this.answer=answer;
		
	}
	
	public String getboardType(){
		return boardType;
	}
	public int getDoc_id(){
		return doc_id;
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
	public String getGen_user_name(){
		return gen_user_name;
	}
	public String getAnswer(){
		return answer;
	}
	
	
	
	public void setboardType(String boardType){
		this.boardType=boardType;
	}
	public void setDoc_id(int doc_id){
		this.doc_id=doc_id;
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
	public void setGen_user_name(String gen_user_name){
		this.gen_user_name=gen_user_name;
	}
	public void setAnswer(String answer){
		this.answer=answer;
	}

}
