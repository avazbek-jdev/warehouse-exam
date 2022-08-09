package ai.ecma.warehouseexam.entity;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "employee")
@SQLDelete(sql = "update employee set deleted=true and active=false where id=?")
// @SQLUpdate(sql = "update employee set active=false where id=?")
@Where(clause = "deleted=false")
public class Employee extends AbsEntity implements UserDetails {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String ishchiRaqami;

    private String password;

    @ManyToOne
    private Warehouse warehouse;

    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    private boolean enabled;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = role.getPermissions()
                .stream()
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name()))
                .collect(Collectors.toList());

//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//        for (PermissionEnum permission : role.getPermissions()) {
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission.name());
//            simpleGrantedAuthorities.add(simpleGrantedAuthority);
//        }

        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return firstName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
