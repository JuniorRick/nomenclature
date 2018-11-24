//package crdm.nomenclature.service;
//
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//
//import crdm.nomenclature.entity.User;
//
//public class CustomUserDetails extends org.springframework.security.core.userdetails.User{
//
//	private static final long serialVersionUID = 1L;
//
//	private User user;
//
//    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
//        super(user.getEmail(), user.getPassword(), authorities);
//        this.user = user;
//    }
//
//    public CustomUserDetails(User user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(user.getEmail(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//        this.user = user;
//    }
//
//    public User getUser() {
//        return user;
//    }
//}