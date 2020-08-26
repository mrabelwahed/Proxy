package com.ramadan.proxy.lib;

public class UrlProxyInfo {
    private String regex;
    private ProxyInfo proxyInfo;

    public UrlProxyInfo(String regex, ProxyInfo proxyInfo) {
        this.regex = regex;
        this.proxyInfo = proxyInfo;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public ProxyInfo getProxyInfo() {
        return proxyInfo;
    }

    public void setProxyInfo(ProxyInfo proxyInfo) {
        this.proxyInfo = proxyInfo;
    }

    @Override
    public String toString() {
        return "UrlProxyInfo{" +
                "regex='" + regex + '\'' +
                ", proxyInfo=" + proxyInfo +
                '}';
    }
}