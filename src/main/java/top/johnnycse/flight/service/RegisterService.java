package top.johnnycse.flight.service;

public interface RegisterService {
    public boolean isExist(String userName);

    public boolean register(String userName,String password);
}
