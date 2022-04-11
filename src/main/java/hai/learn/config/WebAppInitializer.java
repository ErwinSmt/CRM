package hai.learn.config;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

    public class WebAppInitializer implements WebApplicationInitializer{

        private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {

            // create the spring application context
            AnnotationConfigWebApplicationContext context =
                    new AnnotationConfigWebApplicationContext();
            context.register(WebConfig.class);

            // create the dispatcher servlet
            DispatcherServlet dispatcherServlet =
                    new DispatcherServlet(context);

            // register and configure the dispatcher servlet
            ServletRegistration.Dynamic registration =
                    servletContext.addServlet(DISPATCHER_SERVLET_NAME,dispatcherServlet);

            registration.setLoadOnStartup(1);
            registration.addMapping("/");

        }

    }

