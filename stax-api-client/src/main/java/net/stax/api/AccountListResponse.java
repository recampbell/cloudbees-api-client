/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("AccountListResponse")
public class AccountListResponse {
    private List<AccountInfo> accounts;

    public AccountListResponse() {
         accounts = new ArrayList<AccountInfo>();
    }

    public List<AccountInfo> getAccounts() {
        return accounts;
    }

    public void setAccountInfos(List<AccountInfo> infos) {
        this.accounts = infos;
    }

    public void addAccountInfo(AccountInfo accountInfo) {
        this.accounts.add(accountInfo);
    }
}