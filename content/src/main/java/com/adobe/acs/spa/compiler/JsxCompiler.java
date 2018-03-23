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
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Compile ES6/JSX to Javascript Relies on - babel.js for processing the content - Nashorn (requires Java 8 or later)
 */
@Component
@Service(value = ScriptCompiler.class)
public class JsxCompiler implements ScriptCompiler {

    private static final transient Logger LOG = LoggerFactory.getLogger(JsxCompiler.class);

    ExecutorService asyncInitOperations;
    Future<ScriptEngine> babelEngine;

    @Activate
    public void activate(BundleContext context) throws ScriptException, IOException {
        asyncInitOperations = Executors.newSingleThreadExecutor();
        babelEngine = asyncInitOperations.submit(() -> {
            LOG.debug("Entering JSX Compiler init");
            ScriptEngine engine = new ScriptEngineManager().getEngineByMimeType("text/javascript");
            loadScript(engine, "/patch/nashorn-polyfills.js");
            loadScript(engine, "/patch/es6-shim.js");
            loadScript(engine, "/babel/babel.js");
            loadScript(engine, "/react/react.development.js");
            loadScript(engine, "/react/react-dom.development.js");
            asyncInitOperations.shutdown();
            LOG.debug("JSX Compiler init completed");
            return engine;
        });
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
    @Override
    public String getOutputExtension() {
        return "js";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compile(Collection<ScriptResource> scriptResources, Writer writer, CompilerContext ctx) throws IOException {
        String lastScript = "";
        try {
            synchronized (babelEngine) {
                Bindings bindings = babelEngine.get().getBindings(ScriptContext.ENGINE_SCOPE);
                for (ScriptResource res : scriptResources) {
                    lastScript = res.getName();
                    StringWriter outCatcher = new StringWriter();
                    StringWriter errCatcher = new StringWriter();
                    babelEngine.get().getContext().setErrorWriter(errCatcher);
                    babelEngine.get().getContext().setWriter(outCatcher);

                    String inputScript = IOUtils.toString(res.getReader());
                    bindings.put("input", inputScript);
                    //, plugins:['transform-es2015-modules-umd'] }
                    Object output = babelEngine.get().eval("Babel.transform(input, { presets: ['react', 'es2015']}).code", bindings);
                    if (output != null) {
                        writer.append(String.valueOf(output));
                    }

                    if (outCatcher.getBuffer().length() > 0) {
                        LOG.warn("Intecepted Babel output from script {0}", res.getName());
                        LOG.warn(outCatcher.getBuffer().toString());
                    }
                    if (errCatcher.getBuffer().length() > 0) {
                        LOG.error("Intecepted Babel error from script {0}", res.getName());
                        LOG.error(errCatcher.getBuffer().toString());
                    }
                    babelEngine.get().getContext().setErrorWriter(null);
                    babelEngine.get().getContext().setWriter(null);
                }
            }
        } catch (ScriptException | InterruptedException | ExecutionException ex) {
            LOG.error(MessageFormat.format("Exception occured during JSX compilation of {0}", lastScript), ex);
        }
    }

    private void loadScript(ScriptEngine engine, String path) throws ScriptException {
        InputStream babelScriptIn = getClass().getResourceAsStream(path);
        InputStreamReader babelScript = new InputStreamReader(babelScriptIn, StandardCharsets.UTF_8);
        engine.eval(babelScript);
    }
}
