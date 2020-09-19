package com.dxc.bookshelf.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.bookshelf.user.model.User;

/**
 * @author sgangal2
 *
 */
@Repository
//@Repository annotation is used to indicate that the class provides the mechanism for storage,
//retrieval, search, update and delete operation on objects.

//<User, String>--in the class of user,userId is declared as string.

public interface UserRepository extends JpaRepository<User, String>
{

		

}
