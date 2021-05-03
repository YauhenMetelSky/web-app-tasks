package by.metelski.webtask.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;


public class FindUsersByNameCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl();

	@Override
	public Router execute(HttpServletRequest request) {
		List<User> users;
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		String userName = request.getParameter(ParameterAndAttribute.USER_NAME);
		logger.log(Level.INFO, "Find by name: " + userName);
		try {
			users = userService.findUsersByName(userName);
			if (users.size() > 0) {
				router.setPagePath(page);
				request.setAttribute(ParameterAndAttribute.LIST, users);
			} else {
				router.setPagePath(page);
				request.setAttribute(ParameterAndAttribute.MESSAGE, "Can't find such user");
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute");
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
