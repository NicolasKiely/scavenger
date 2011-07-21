package me.ryall.scavenger.economy;

// working off assumptions:
// main bank account is bank account being used
// holdings is the proper money of the account
import org.bukkit.plugin.Plugin;


import com.iConomy.iConomy;

import com.iConomy.system.Account;


public class IConomyFourAdapter extends EconomyInterface
{
    public IConomyFourAdapter(Plugin _plugin)
    {
    }

    public String getName()
    {
        return "iConomy 4";
    }

    public double getBalance(String _playerName)
    {
        Account acc = iConomy.getAccount(_playerName);

        /* Modified, not sure if correct though */
        return (acc == null) ? 0 : acc.getHoldings().balance();
    }

    public boolean canAfford(String _playerName, double _amount)
    {
        if (_amount == 0)
            return true;

        Account acc = iConomy.getAccount(_playerName);

        return (acc == null) ? false : acc.getHoldings().hasEnough(_amount);
    }

    public boolean add(String _playerName, double _amount)
    {
        Account acc = iConomy.getAccount(_playerName);

        if (acc != null)
        {
            acc.getHoldings().add(_amount);
            return true;
        }

        return false;
    }

    public boolean subtract(String _playerName, double _amount)
    {
        Account acc = iConomy.getAccount(_playerName);

        if (acc != null)
        {
            acc.getHoldings().subtract(_amount);
            return true;
        }

        return false;
    }

    public boolean transfer(String _playerFrom, String _playerTo, double _amount)
    {
        Account accFrom = iConomy.getAccount(_playerFrom);
        Account accTo = iConomy.getAccount(_playerTo);

        if (accFrom != null && accTo != null)
        {
            accFrom.getHoldings().subtract(_amount);
            accTo.getHoldings().add(_amount);

            return true;
        }

        return false;
    }

    public String formatCurrency(double _amount)
    {
    	return iConomy.format(_amount);
    }
}
