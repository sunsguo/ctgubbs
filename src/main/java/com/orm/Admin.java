package com.orm;

import java.util.HashSet;
import java.util.Set;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String account;
	private String password;
	private Integer qx;
	private String qq;
	private String email;
	private String nickName;
	private String name;
	private String photoPath;
	private Set posts = new HashSet(0);
	private Set boards = new HashSet(0);
	private Set replies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(String account, String password, Integer qx, String accound) {
		this.account = account;
		this.password = password;
		this.qx = qx;
	}

	/** full constructor */
	public Admin(String account, String password, Integer qx, String nickName,
			String name, String photoPath,String qq, String email, String accound, Set posts,
			Set boards, Set replies) {
		this.account = account;
		this.password = password;
		this.qx = qx;
		this.nickName = nickName;
		this.name = name;
		this.qq = qq;
		this.email = email;
		this.photoPath = photoPath;
		this.posts = posts;
		this.boards = boards;
		this.replies = replies;
	}

	// Property accessors
	
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getQq() {
		return qq;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getQx() {
		return this.qx;
	}

	public void setQx(Integer qx) {
		this.qx = qx;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Set getPosts() {
		return this.posts;
	}

	public void setPosts(Set posts) {
		this.posts = posts;
	}

	public Set getBoards() {
		return this.boards;
	}

	public void setBoards(Set boards) {
		this.boards = boards;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}


}