package com.gucardev.springlearning.mdc_log_exception;


// calisiyor gibi
//@Component
//public class CustomizeApplicationLoggingInterceptor implements HandlerInterceptor {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizeApplicationLoggingInterceptor.class);
//    private static final String TRACE_ID_LOG_VAR_NAME = "traceId";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestId = UUID.randomUUID().toString().substring(0, 13);
//        MDC.put(TRACE_ID_LOG_VAR_NAME, requestId);
//        LOGGER.info("Received request with ID: {}", requestId);
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        String requestId = MDC.get(TRACE_ID_LOG_VAR_NAME);
//        LOGGER.info("Request with ID {} completed", requestId);
//        MDC.remove(TRACE_ID_LOG_VAR_NAME);
//    }
//
//}