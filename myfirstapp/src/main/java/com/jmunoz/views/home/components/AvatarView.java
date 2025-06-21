package com.jmunoz.views.home.components;

import com.jmunoz.entity.Employee;
import com.jmunoz.util.DataGenerator;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarGroup;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("avatar")
public class AvatarView extends VerticalLayout {

    AvatarView() {
        Avatar avatar = new Avatar();
        setAvatarProperties(avatar);
        stylingAvatar(avatar);

        // Grupo de avatares.
        AvatarGroup avatarGroup = new AvatarGroup();
        setAvatarGroupProperties(avatarGroup);
        stylingAvatarGroup(avatarGroup);

        add(avatar, avatarGroup);
    }

    private void setAvatarGroupProperties(AvatarGroup avatarGroup) {
        // Indicamos el máximo de avatares que se muestran.
        avatarGroup.setMaxItemsVisible(3);

        // Los items del grupo de avatares.
        // Si pasamos el ratón por el avatar, sale el nombre por defecto.
        // Si no se muestran todos los avatares porque hemos indicado un máximo menor a los items que hay,
        //    si se pulsa Intro en ese Avatar que contiene el número, se ven los avatares con sus nombres.
        for (Employee employee : DataGenerator.getEmployees()) {
            AvatarGroup.AvatarGroupItem item = new AvatarGroup.AvatarGroupItem();
            item.setName(employee.getName());
            item.setColorIndex((int) employee.getId());
//            item.setImage("images/" + employee.getId() + ".png");
            avatarGroup.add(item);
        }
    }

    private void stylingAvatarGroup(AvatarGroup avatarGroup) {
        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/avatarStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        avatarGroup.addClassName("avt-grp");
        avatarGroup.setOverlayClassName("avt-grp-overlay");
    }

    private void setAvatarProperties(Avatar avatar) {
        // Se muestra el avatar con la abreviatura por defecto del nombre, J en este caso.
        avatar.setName("John");

        // Para que aparezca el nombre como ayuda al pasar el ratón por el avatar.
        // Por defecto a false.
        avatar.setTooltipEnabled(true);

        // Podemos indicar la abreviatura que queramos.
        avatar.setAbbreviation("JH");

        // Indicamos el color de fondo.
        // Va de 1 a 6.
        // Puede indicarse el color que se quiera usando CSS.
        // Si indicamos esta propiedad y cambiamos el fondo queda un borde del color del index.
        // Salvo que queramos eso, mejor usar CSS.
//        avatar.setColorIndex(4);

        // Podemos indicar la imagen que queramos, en vez de que solo salga la abreviatura.
        avatar.setImage("images/3.png");
    }

    private void stylingAvatar(Avatar avatar) {
        // THEMES VARIANTS
//        avatar.addThemeVariants(AvatarVariant.LUMO_XSMALL);

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/avatarStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        avatar.addClassName("user-image");
    }
}
