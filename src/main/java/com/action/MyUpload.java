package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Board;
import com.orm.Student;
import com.service.IBoardService;
import com.service.IStudentService;

public class MyUpload extends ActionSupport{
	@Resource(name="studentService")
	IStudentService studentService;
	@Resource(name="boardService")
	IBoardService boardService;
	//封装上传文件 的属性
	private File doc;
	//封装文件类型
	private String docContentType;
	//封装文件名,以系统当前秒时间命名
	private String docFileName;
	
	//struts拦截器中配置
	private String path;
	
	public File getDoc() {
		return doc;
	}
	public void setDoc(File doc) {
		this.doc = doc;
	}
	public String getDocContentType() {
		return docContentType;
	}
	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}
	public String getDocFileName() {
		return docFileName;
	}
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}
	public String getPath() throws Exception{
		return ServletActionContext.getServletContext().getRealPath(path);
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String myUpload() throws Exception {
		// TODO Auto-generated method stub
		this.docFileName = this.getFileName(docFileName);
		FileOutputStream fos = new FileOutputStream(getPath() + File.separator + this.docFileName);
		FileInputStream fis = new FileInputStream(this.doc);
		byte[] b = new byte[1024];
		int length = 0;
		while((length = fis.read(b)) > 0){
			fos.write(b,0,length);
		}
		//将头像路径写入数据库
		Student stu = (Student)ActionContext.getContext().getSession().get("student");
		if(stu != null){
			Student temp = this.studentService.getStudentByStuNum(stu.getStuNum());
			temp.setPhotoPath(this.getDocFileName());
			this.studentService.modifyStudent(temp);
		}
		return super.SUCCESS;
	}
	public String boardImgUpload() throws Exception {
		this.docFileName = this.getFileName(docFileName);
		FileOutputStream fos = new FileOutputStream(getPath() + "\\" + this.docFileName);
		FileInputStream fis = new FileInputStream(this.doc);
		byte[] b = new byte[1024];
		int length = 0;
		while((length = fis.read(b)) > 0){
			fos.write(b,0,length);
		}
		if(ServletActionContext.getRequest().getParameter("bid") != null){
			int bid = Integer.parseInt(ServletActionContext.getRequest().getParameter("bid"));
			Board board = this.boardService.loadBoard(bid);
			board.setBoardImg(this.docFileName);
			if(this.boardService.savaOrUpdateBoard(board)){
				return super.SUCCESS;
			}
			return super.ERROR;
		}
		if(ServletActionContext.getRequest().getParameter("stuNum") != null){
			String stuNum = ServletActionContext.getRequest().getParameter("stuNum");
			Student student = this.studentService.getStudentByStuNum(stuNum);
			student.setPhotoPath(this.docFileName);
			if(this.studentService.modifyStudent(student)){
				return "modifyOk";
			}
		}
		return super.ERROR;
	}
	//获得文件扩展名
	private String getFileName(String fileName){
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return System.currentTimeMillis() + extension;	
	}
}






















