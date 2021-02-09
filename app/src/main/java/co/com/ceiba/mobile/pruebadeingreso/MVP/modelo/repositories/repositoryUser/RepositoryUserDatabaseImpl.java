package co.com.ceiba.mobile.pruebadeingreso.MVP.modelo.repositories.repositoryUser;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.MVP.modelo.DAO.database.databasePost.PostDB;
import co.com.ceiba.mobile.pruebadeingreso.MVP.modelo.DAO.database.databaseUser.UserDB;
import co.com.ceiba.mobile.pruebadeingreso.MVP.modelo.entitis.PostUser;
import co.com.ceiba.mobile.pruebadeingreso.MVP.modelo.entitis.User;
import co.com.ceiba.mobile.pruebadeingreso.MVP.presenter.PresenterUser;

public class RepositoryUserDatabaseImpl implements RepositoryUser {

    private PresenterUser presenterUser;
    private UserDB userDB;
    private PostDB postDB;
    List<User> listUsers = new ArrayList<>();

    private List<PostUser> listPost = new ArrayList<>();


    public RepositoryUserDatabaseImpl(Context context, PresenterUser presenterUser) {
        this.presenterUser = presenterUser;
        userDB = UserDB.getInstance(context);
        postDB = PostDB.getInstance(context);
    }
    /**
     * DEVOLVEMOS VALORES NULOS SIMULANDO QUE DB NO TIENE REGISTROS
     * **/

    @Override
    public void getUsers() {
        Log.d("RepositoryUserDBImpl","getUser");

        listUsers = userDB.getUser();
        if (listUsers== null) listUsers = new ArrayList<>();
        presenterUser.showUserDB(listUsers);

    }

    @Override
    public void setUser(List<User> userList) {
        this.listUsers = userList;
        Log.d("RepositoryUserDBImpl","setUser");
        for (int i=0; i<userList.size(); i++){
            userDB.setUser(userList.get(i));
        }
    }

    @Override
    public List<User> getUserSaved() {
        return listUsers;
    }

    @Override
    public List<User> userFiltered(String stringFiltered) {
        stringFiltered = stringFiltered.toLowerCase();
        List<User> userListFiltered = new ArrayList<>();
        if (stringFiltered.length()==0){
            userListFiltered.addAll(listUsers);
            if (userListFiltered.isEmpty());
            return userListFiltered;
        }

        for (User user: listUsers){
            if (user.getName().toLowerCase().contains(stringFiltered)) userListFiltered.add(user);
        }

        return userListFiltered;
    }

    @Override
    public void getPostUserId(String userId) {
       postDB.getPostById(userId);
    }

    @Override
    public void getAllPost() {
        presenterUser.showAllPostDB(postDB.getPost());
    }

    @Override
    public void setAllPost(List<PostUser> listPost) {
        for (PostUser postUser : listPost){
            postDB.setPost(postUser);
        }
    }

    @Override
    public boolean verificarConexion() {
        return false;
    }


    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<PostUser> getListPost() {
        return listPost;
    }

    public void setListPost(List<PostUser> listPost) {
        this.listPost = listPost;
    }
}
