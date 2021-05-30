package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class UnblockUserCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
	    Router router = new Router();
	    boolean isBlocked = false;
	    logger.log(Level.DEBUG, "execute method UnblockUserCommand");
		long id = Long.parseLong(request.getParameter(ParameterAndAttribute.USER_ID));		
		try {
			String page = request.getContextPath() +PagePath.TO_PERSONAL_PAGE;
			isBlocked = userService.changeUserIsBlocked(id, false);
			if (isBlocked) {
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
				//TODO session attribute message user blocked
			} else {
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
				//TODO session attribute message not blocked"
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute BlockUserCommand" + e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}