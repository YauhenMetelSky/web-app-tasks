package by.metelski.webtask.command.async;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

public class FindScheduleByIdAsyncCommand implements Command{
	private static final Logger logger = LogManager.getLogger();
	private ScheduleService service = new ScheduleServiceImpl(new ScheduleDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response){
		logger.log(Level.DEBUG, "FindStartEndTimebyScheduleIdCommand");
		response.setContentType("application/json");
		DoctorSchedule schedule;
		Router router = new Router();
		Long scheduleId = Long.parseLong(request.getParameter(ParameterAndAttribute.SCHEDULE_ID));
		try {//FIXME empty optional
			schedule =service.findScheduleById(scheduleId).get();		
			String scheduleGson = new Gson().toJson(schedule);
			logger.log(Level.DEBUG, "string gson: " + scheduleGson);
			 response.getWriter().write(scheduleGson);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ScheduleServiceException in method execute");
			router.setPagePath(PagePath.ERROR);
		}catch(IOException e1) {
			logger.log(Level.ERROR, "IOException in method execute" + e1);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
