package raven.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import raven.drawer.component.DrawerPanel;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.header.SimpleHeaderStyle;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.drawer.component.menu.SimpleMenuStyle;
import raven.forms.DashboardForm;
import raven.forms.InboxForm;
import raven.swing.AvatarIcon;

/**
 * MyDrawerBuilder es una implementación personalizada de SimpleDrawerBuilder
 * que personaliza la apariencia y el comportamiento del cajón lateral (drawer)
 * en la aplicación. Extiende SimpleDrawerBuilder y proporciona detalles
 * específicos de construcción del menú, el encabezado y el pie de página.
 *
 * @author Esteban
 */
public class MyDrawerBuilder extends SimpleDrawerBuilder {

    private final ThemesChange themesChange;

    /**
     * Constructor de MyDrawerBuilder. Inicializa la instancia de ThemesChange.
     */
    public MyDrawerBuilder() {
        themesChange = new ThemesChange();
    }

    /**
     * Obtiene el componente del pie de página personalizado (ThemesChange).
     *
     * @return El componente del pie de página.
     */
    @Override
    public Component getFooter() {
        return themesChange;
    }

    /**
     * Obtiene los datos personalizados del encabezado (SimpleHeaderData) para
     * el cajón lateral.
     *
     * @return Los datos del encabezado personalizados.
     */
    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        AvatarIcon icon = new AvatarIcon(getClass().getResource("/raven/resources/image/medac.jpg"), 60, 60, 999);
        icon.setBorder(2);
        return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle("Esteban")
                .setDescription("Medac@Medac.com")
                .setHeaderStyle(new SimpleHeaderStyle() {

                    @Override
                    public void styleTitle(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#FAFAFA");
                    }

                    @Override
                    public void styleDescription(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#E1E1E1");
                    }
                });
    }

    /**
     * Obtiene los datos personalizados del pie de página (SimpleFooterData)
     * para el cajón lateral.
     *
     * @return Los datos del pie de página personalizados.
     */
    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData();
    }
    /**
     * Obtiene la opción de menú personalizada (SimpleMenuOption) que define el estilo y la estructura del menú del cajón lateral.
     * 
     * @return La opción de menú personalizada.
     */
    @Override
    public SimpleMenuOption getSimpleMenuOption() {

        String menus[][] = {
            {"~INICIO~"},
            {"Grafica"},
            {"~CONTROL FINANCIERO~"},
            {"Datos", "Registros"}
        };

        String icons[] = {
            "dashboard.svg",
            "page.svg",
            "chat.svg",
            "calendar.svg",
            "ui.svg",
            "forms.svg",
            "chart.svg",
            "icon.svg",
            "page.svg",};

        SimpleMenuOption simpleMenuOption = new SimpleMenuOption() {
            @Override
            public Icon buildMenuIcon(String path, float scale) {
                FlatSVGIcon icon = new FlatSVGIcon(path, scale);
                FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
                colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
                icon.setColorFilter(colorFilter);
                return icon;
            }
        };

        simpleMenuOption.setMenuStyle(new SimpleMenuStyle() {
            @Override
            public void styleMenuItem(JButton menu, int index) {
                menu.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:#FAFAFA;"
                        + "arc:10");
            }

            @Override
            public void styleSubMenuItem(JButton subMenu, int index, int subIndex) {
                subMenu.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:#FAFAFA;"
                        + "arc:10");
            }

            @Override
            public void styleMenu(JComponent component) {
                component.putClientProperty(FlatClientProperties.STYLE, ""
                        + "background:$Drawer.background");
            }

            @Override
            public void styleLabel(JLabel label) {
                label.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:darken(#FAFAFA,15%);"
                        + "[dark]foreground:darken($Label.foreground,30%)");
            }
        });
        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int index, int subIndex) {
                if (index == 0) {
                    FormManager.showForm(new DashboardForm());
                } else if (index == 1) {
                    if (subIndex == 1) {
                        FormManager.showForm(new InboxForm());
                    }
                }
            }
        });

        simpleMenuOption.setMenus(menus)
                .setIcons(icons)
                .setBaseIconPath("raven/resources/menu")
                .setIconScale(0.45f);
        return simpleMenuOption;
    }
    /**
     * Construye el cajón lateral utilizando el DrawerPanel proporcionado.
     * 
     * @param drawerPanel El DrawerPanel utilizado para construir y personalizar el cajón lateral.
     */
    @Override
    public void build(DrawerPanel drawerPanel) {
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Drawer.background");
    }

    /**
     * Obtiene el ancho deseado del cajón lateral.
     * 
     * @return El ancho del cajón lateral.
     */
    @Override
    public int getDrawerWidth() {
        return 270;
    }
}
