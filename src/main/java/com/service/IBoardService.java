package com.service;

import java.util.List;

import com.orm.Board;

public interface IBoardService {
	/*
	 * 加载Board
	 */
	public Board loadBoard(int id);
	/*
	 * 加载子板块
	 */
	public List<Board> loadChildBoards(int parentId);
	/*
	 * 加载所有板块
	 */
	public List<Board> loadAllBoards();
	/*
	 * 加载根板块
	 */
	public List<Board> loadRootBoards();
	/*
	 * 保存更新板块
	 */
	public boolean savaOrUpdateBoard(Board board);
}
