/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adobe.acs.spa.compiler;

import com.adobe.granite.ui.clientlibs.script.CompilerContext;
import com.adobe.granite.ui.clientlibs.script.ScriptCompiler;
import com.adobe.granite.ui.clientlibs.script.ScriptResource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.commons.io.IOUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.BundleContext;

/**
 * Compile ES6/JSX to Javascript Relies on - babel.js for processing the content - Nashorn (requires Java 8 or later)
 */
@Component
@Service(value = ScriptCompiler.class)
public class JsxCompiler implements ScriptCompiler {

    ScriptEngine babelEngine;

    @Activate
    public void activate(BundleContext context) throws ScriptException, IOException {
        babelEngine = new ScriptEngineManager().getEngineByMimeType("text/javascript");
        loadScript(babelEngine, "/patch/nashorn-polyfills.js");
        loadScript(babelEngine, "/patch/es6-shim.js");
        loadScript(babelEngine, "/babel/babel.js");
        loadScript(babelEngine, "/react/react.development.js");
        loadScript(babelEngine, "/react/react-dom.development.js");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Babel Javascript Compiler";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMimeType() {
        return "application/javascript";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean handles(String ext) {
        switch (ext) {
            case "es6":
            case "es":
            case "jsx":
                return true;
            default:
                return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getOutputExtension() {
        return "js";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compile(Collection<ScriptResource> scriptResources, Writer writer, CompilerContext ctx) throws IOException {
        try {
            Bindings bindings = babelEngine.getBindings(ScriptContext.ENGINE_SCOPE);
            //SimpleBindings bindings = new SimpleBindings();
            for (ScriptResource res : scriptResources) {
                String inputScript = IOUtils.toString(res.getReader());
                bindings.put("input", inputScript);
                //, plugins:['transform-es2015-modules-umd'] }
                Object output = babelEngine.eval("Babel.transform(input, { presets: ['react', 'es2015']}).code", bindings);
                if (output != null) {
                    writer.append(String.valueOf(output));
                }
            }
        } catch (ScriptException ex) {
            Logger.getLogger(JsxCompiler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadScript(ScriptEngine engine, String path) throws ScriptException {
        InputStream babelScriptIn = getClass().getResourceAsStream(path);
        InputStreamReader babelScript = new InputStreamReader(babelScriptIn, StandardCharsets.UTF_8);
        engine.eval(babelScript);
    }
}
