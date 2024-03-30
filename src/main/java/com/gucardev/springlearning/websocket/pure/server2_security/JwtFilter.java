package com.gucardev.springlearning.websocket.pure.server2_security;

//@Component
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//  private final TokenService tokenService;
//  private final UserDetailsServiceImpl userDetailsService;

//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain)
//            throws ServletException, IOException {
//    final String jwt;
//    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//    if (header != null && header.startsWith("Bearer ")) {
//      jwt = header.substring(7);
//    } else {
//      // BURASI WEBSOCKET ICIN
//      jwt = request.getParameter("token");
//    }
//
//    if (jwt == null) {
//      filterChain.doFilter(request, response);
//      return;
//    }
//    DecodedJWT decodedJWT;
//    String username;
//    try {
//      decodedJWT = tokenService.verifyJWT(jwt);
//      username = decodedJWT.getSubject();
//    } catch (Exception e) {
//      sendError(response, new Exception("token is not valid!"));
//      return;
//    }
//
//    if (username == null) {
//      filterChain.doFilter(request, response);
//      return;
//    }
//
//    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//    UsernamePasswordAuthenticationToken authToken =
//        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//    SecurityContextHolder.getContext().setAuthentication(authToken);
//    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//    filterChain.doFilter(request, response);
//    }

//  private void sendError(HttpServletResponse res, Exception e) throws IOException {
//    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//    res.setContentType("application/json");
//    PrintWriter out = res.getWriter();
//    ObjectMapper mapper = new ObjectMapper();
//    out.println(mapper.writeValueAsString(Map.of("error", e.getMessage(), "code", "-1")));
//    out.flush();
//  }
//}
