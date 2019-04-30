package com.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.orm.Admin;
import com.orm.Board;
import com.service.IAdminService;
import com.service.IBoardService;

public class BoardAction extends ActionSupport {
	@Resource(name="boardService")
	IBoardService boardService;
	@Resource(name="adminService")
	IAdminService adminService;
	
	private List<Board> boardList;
	private List<Board> childBoards;	//子节点
	private List<Board> rootBoards;	//根节点
	private Board board;
	public List<Board> getBoardList() {
		return boardList;
	}
	public void setBoardList(List<Board> boardList) {
		this.boardList = boardList;
	}
	public List<Board> getChildBoards() {
		return childBoards;
	}
	public void setChildBoards(List<Board> childBoards) {
		this.childBoards = childBoards;
	}
	public List<Board> getRootBoards() {
		return rootBoards;
	}
	public void setRootBoards(List<Board> rootBoards) {
		this.rootBoards = rootBoards;
	} 
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}

	public String board() throws Exception {
		// TODO Auto-generated method stub
		try{
			this.loadRootBoards();
			return super.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return super.ERROR;
		}
	}
	//加载所有板块
	public String listBoards() throws Exception{
		try{
			this.setBoardList(this.boardService.loadAllBoards());
			return super.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return super.ERROR;
		}
	}
	//加载二级板块
	public String listChildBoards() throws Exception{
		int parentId = Integer.parseInt(ServletActionContext.getRequest().getParameter("parentId"));
		try{
			this.loadRootBoards();
			this.setChildBoards(this.boardService.loadChildBoards(parentId));
			return super.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return super.ERROR;
		}
	}
	//加载跟板块
	public String loadRootBoards() throws Exception{
		try{
			this.setRootBoards(this.boardService.loadRootBoards());
			return super.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return super.ERROR;
		}
	}
	//跳转添加板块
	public String prepareAddBoard() throws Exception {
		try{
			//this.setBoardList(this.boardService.loadAllBoards());
			this.setRootBoards(this.boardService.loadRootBoards());
			return "addBoard";
		}catch(Exception e){
			e.printStackTrace();
			return super.ERROR;
		}
	}
	
	//添加板块
	public String addBoard() throws Exception{
		Admin admin = (Admin)ServletActionContext.getRequest().getSession().getAttribute("admin");
		/*
		 * 获取板块id
		 * 判断添加以及板块或下一级板块，首先去除parentId
		 */
		int bid = this.getBoard().getId();
		int parentId = 0;
		Board tempBoard = new Board();
		/*
		 * 赋值的时候一定要判断是否为空
		 * 有些空指针异常是由session失效引起的
		 */
		try{
			if(bid == -1){
				tempBoard.setName(getBoard().getName());
				tempBoard.setAdmin(admin);
				tempBoard.setDescription(getBoard().getDescription());
				//提交
				if(this.boardService.savaOrUpdateBoard(tempBoard)){
					return "addSuccess";
				}
				return super.ERROR;
			}else{	//处理二级板块
				parentId = this.boardService.loadBoard(bid).getId(); 
				Board board = this.boardService.loadBoard(bid);
				tempBoard.setBoard(board);
				tempBoard.setName(getBoard().getName());
				tempBoard.setAdmin(admin);
				tempBoard.setDescription(getBoard().getDescription());
				//提交
				if(this.boardService.savaOrUpdateBoard(tempBoard)){
					return "addSuccess";
				}
				return super.ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return super.ERROR;
		}
	}


	public String prepareMOdifyBoard() throws Exception{
		//获取id
		HttpServletRequest request = ServletActionContext.getRequest();
		int bid = 0;
		if(request.getParameter("bid") != null){
			bid = Integer.parseInt(request.getParameter("bid"));
		}
		this.setBoard(this.boardService.loadBoard(bid));
		this.loadRootBoards();
		return "prepareModifyBoard";
	}
}




















