package io.idden.nickreloaded.jframe;

import javax.swing.*;

/**
 * Make a pop-up when double clicked on .jar file.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class NickReloadedTutorial
{
    /*
       This can help if someone is
       in trouble installing the plugin,
       so i'm making it ! :)
     */
    public static void main(String[] args)
    {
        JOptionPane.showMessageDialog(null, "Put \"NickReloaded-X.X-xxx.jar\" into your \"/plugins\" folder !", "Nah, this isn't executable :(", JOptionPane.INFORMATION_MESSAGE);
    }
}
