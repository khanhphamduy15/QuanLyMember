package sms.member;

import objects.*;
import util.*;
import java.sql.*;
import java.util.ArrayList;
public interface MemberFunction {
	public boolean addMember(MemberObject item);
	public boolean editMember(MemberObject item);
	public boolean delMember(MemberObject item);
	
	public MemberObject getMember(int id);
	public MemberObject getMember(String username, String userpass);
	public ArrayList<MemberObject> getMembers(MemberObject similar, int at, byte total);
}
