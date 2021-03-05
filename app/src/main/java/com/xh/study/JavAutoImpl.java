package com.xh.study;

import com.google.auto.service.AutoService;

import org.jetbrains.annotations.NotNull;

@AutoService(IAuto.class)
public class JavAutoImpl implements IAuto {
    @NotNull
    @Override
    public String getName() {
        return "JavAutoImpl";
    }
}
