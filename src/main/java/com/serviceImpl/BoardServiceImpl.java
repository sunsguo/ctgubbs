package com.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.orm.Board;
import com.service.IBoardService;

public class BoardServiceImpl implements IBoardService {
	@Resource(name="dao")
	BaseDao dao;
	
	@Override
	public Board loadBoard(int id) {
		// TODO Auto-generated method stub
		return (Board)dao.loadById(Board.class, new Integer(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Board> loadChildBoards(int parentId) {
		// TODO Auto-generated method stub
		final String queryChilds = "from Board as b where b.board = "+parentId+" order by b.id asc";
		return dao.query(queryChilds);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Board> loadAllBoards() {
		// TODO Auto-generated method stub
		return dao.listAll("Board");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Board> loadRootBoards() {
		// TODO Auto-generated method stub
		return dao.query("from Board as b where b.board is null order by b.id asc");
	}

	@Override
	public boolean savaOrUpdateBoard(Board board) {
		// TODO Auto-generated method stub
		try{
			dao.saveOrUpdate(board);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
