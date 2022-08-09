package ai.ecma.warehouseexam.component;

import ai.ecma.warehouseexam.entity.Role;
import ai.ecma.warehouseexam.entity.User;
import ai.ecma.warehouseexam.enums.PermissionEnum;
import ai.ecma.warehouseexam.repository.RoleRepository;
import ai.ecma.warehouseexam.repository.UserRepository;
import ai.ecma.warehouseexam.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {

        if (mode.equals("always")) {
            createUserRoler();
            createSuperAdminUser();
        }
    }

    private void createUserRoler() {
        Role role = new Role(
                AppConstant.USER_ROLE,
                "user role",
                List.of(PermissionEnum.VIEW_INCOMEWAREHOUSE,
                        PermissionEnum.VIEW_WAREHOUSE,
                        PermissionEnum.VIEW_CURRENCIES,
                        PermissionEnum.VIEW_OUTCOME_WAREHOUSE,
                        PermissionEnum.VIEW_CURRENCY,
                        PermissionEnum.VIEW_INCOME_PRODUCT,
                        PermissionEnum.VIEW_INCOME_PRODUCTS,
                        PermissionEnum.VIEW_INCOMEWAREHOUSES,
                        PermissionEnum.VIEW_OUTCOME_PRODUCTS,
                        PermissionEnum.VIEW_OUTCOME_PRODUCT,
                        PermissionEnum.VIEW_OUTCOME_WAREHOUSES,
                        PermissionEnum.VIEW_WAREHOUSES,
                        PermissionEnum.VIEW_MEASUREMENT,
                        PermissionEnum.VIEW_MEASUREMENTS,
                        PermissionEnum.VIEW_PRODUCT,
                        PermissionEnum.VIEW_PRODUCTS,
                        PermissionEnum.VIEW_CATEGORIES,
                        PermissionEnum.VIEW_CATEGORY)
        );
        roleRepository.save(role);
    }

    private void createSuperAdminUser() {
        Role adminRole = new Role(
                AppConstant.ADMIN_ROLE,
                (AppConstant.ADMIN_ROLE + " - bu super admin"),
                Arrays.asList(PermissionEnum.values())
        );

        roleRepository.save(adminRole);

        User admin = new User(
                "admin",
                "admin",
                "+998946328802",
                "avazbeksaxobitdinov@gmail.com",
                passwordEncoder.encode("root123"),
                adminRole,
                null
        );
        admin.setEnabled(true);
        userRepository.save(admin);
    }
}
