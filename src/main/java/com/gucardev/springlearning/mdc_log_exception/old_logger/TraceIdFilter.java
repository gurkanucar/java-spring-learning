package com.gucardev.springlearning.mdc_log_exception.old_logger;

//@Order(1)
//@Component
//public class TraceIdFilter extends OncePerRequestFilter {
//    private static final String TRACE_ID_HEADER = "X-Trace-Id";
//    private static final String TRACE_ID_LOG_VAR_NAME = "traceId";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        if (Stream.of("/api","/serviceWorker","h2-console").anyMatch(x->request.getServletPath().contains(x)) ) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        try {
//            String traceId = request.getHeader(TRACE_ID_HEADER);
//            if (traceId == null) {
//                traceId = UUID.randomUUID().toString();
//            }
//            MDC.put(TRACE_ID_LOG_VAR_NAME, traceId);
//
//            // Add the traceId to the response header
//            response.addHeader(TRACE_ID_HEADER, traceId);
//
//            // Wrap the request to add the traceId to it
//            String finalTraceId = traceId;
//            HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {
//                @Override
//                public String getHeader(String name) {
//                    if (TRACE_ID_HEADER.equals(name)) {
//                        return finalTraceId;
//                    }
//                    return super.getHeader(name);
//                }
//            };
//
//            filterChain.doFilter(wrappedRequest, response);
//        } finally {
//            MDC.remove(TRACE_ID_LOG_VAR_NAME);
//        }
//    }
//
//
//}
