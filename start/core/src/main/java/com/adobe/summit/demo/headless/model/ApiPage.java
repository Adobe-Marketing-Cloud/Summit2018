package com.adobe.summit.demo.headless.model;

import javax.annotation.Nonnull;

import com.adobe.cq.export.json.ComponentExporter;

public class ApiPage implements ComponentExporter {

    public static final String RESOURCE_TYPE = "headless-demo/components/content/list";

    @Nonnull
    @Override
    public String getExportedType() {
        return null;
    }
}
