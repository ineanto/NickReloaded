package fr.antoinerochas.nickreloaded.core.error;

import fr.antoinerochas.nickreloaded.NickReloaded;
import fr.antoinerochas.nickreloaded.core.logger.NRLPrefix;
import fr.antoinerochas.nickreloaded.core.string.NRMessages;

/**
 * Class used to handle errors with NickReloaded plugin.
 *
 * @author Antoine "Idden" R.
 * @since 2.0-rc1
 */
public class NRErrorHandler
{
    public static NRErrorHandler get()
    {
        return new NRErrorHandler();
    }

    public void handleError(Exception e)
    {
        handleError(e.getMessage() + " (From Stacktrace)");
    }

    public void handleError(String message)
    {
        NickReloaded.getNRLogger().log(NRLPrefix.NONE,
                                       NRMessages.SEPARATOR);
        NickReloaded.getNRLogger().log(NRLPrefix.ERROR, "An error occured !:");
        NickReloaded.getNRLogger().log(NRLPrefix.ERROR, "Reason: \"" + message + "\".");
        NickReloaded.getNRLogger().log(NRLPrefix.ERROR, "The error report has been saved into \"./NickReloaded/errlogs/\".");
        saveDetailledError(message);
        NickReloaded.getNRLogger().log(NRLPrefix.NONE,
                                       NRMessages.SEPARATOR);
        //todo: save de
    }

    public void handleFatal(String message)
    {
        NickReloaded.getNRLogger().log(NRLPrefix.FATAL, "");
    }

    private void saveDetailledError(String message)
    {

    }
}
