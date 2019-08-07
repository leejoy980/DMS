package VO;

public class memberVo {
	
	private String user_id;
	private String pwd;
	private String groupType;
	private String pos;
	private String classType;
	private String club;
	private String grade;
	private String user_name;
	
	public memberVo(String user_id,String pwd,String groupType,String pos,String classType,String club,String grade,String user_name){
		this.user_id=user_id;
		this.pwd=pwd;
		this.groupType=groupType;
		this.pos=pos;
		this.classType=classType;
		this.club=club;
		this.grade=grade;
		this.user_name=user_name;
	}
	
	public String getUser_id(){
		return user_id;
	}
	public String getPwd(){
		return pwd;
	}
	public String getGroupType(){
		return groupType;
	}
	public String getPos(){
		return pos;
	}
	public String getClassType(){
		return classType;
	}
	public String getClub(){
		return club;
	}
	public String getGrade(){
		return grade;
	}
	public String getUser_name(){
		return user_name;
	}
	
	public void setUser_id(String user_id){
		this.user_id=user_id;
	}
	public void setPwd(String pwd){
		this.pwd=pwd;
	}
	public void setGroupType(String groupType){
		this.groupType=groupType;
	}
	public void setPos(String pos){
		this.pos=pos;
	}
	public void setClassType(String classType){
		this.classType=classType;
	}
	public void sestClub(String club){
		this.club=club;
	}
	public void setGrade(String grade){
		this.grade=grade;
	}
	public void setUser_name(String user_name){
		this.user_name=user_name;
	}

}
