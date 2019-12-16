package com.ahuan.service.impl;

import com.ahuan.dao.IUserDao;
import com.ahuan.domain.Role;
import com.ahuan.domain.UserInfo;
import com.ahuan.exception.BusinessException;
import com.ahuan.exception.ResultEnum;
import com.ahuan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)){
            throw new BusinessException(ResultEnum.NOTNULL_ERROR);
        }
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Role> roles = userInfo.getRoles();//该用户角色
        List<SimpleGrantedAuthority> authoritys = getAuthority(roles);
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),
                userInfo.getStatus() == 0?false:true,true,true,true,authoritys);
        return user;
    }
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        List<UserInfo> all = userDao.findAll();
        return all;
    }

    @Override
    public void save(UserInfo user) throws Exception {
        if ("11".equals(user.getPassword())){
            throw new BusinessException(ResultEnum.UNKNOW_ERROR);
        }
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userDao.save(user);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        if (id=="" || id ==null){
            throw new BusinessException(ResultEnum.NOTNULL_ERROR);
        }
        return userDao.findById(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) {
        if (StringUtils.isEmpty(userId) || ids.length <= 0){
            throw new BusinessException(ResultEnum.NOTNULL_ERROR);
        }
        Arrays.stream(ids).filter(p->{
            userDao.addRoleToUser(userId,p);
            return true;
        }).collect(Collectors.toList());
    }
}
