package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;

import dao.ISerieDao;
import dao.SerieDaoImpl;
import metier.entities.Serie;

@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
	ISerieDao metier;
	
	@Override
	public void init() throws ServletException {
		metier = new SerieDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if (path.equals("/index.do"))
		{
			request.getRequestDispatcher("series.jsp").forward(request,response);
		}
		else if (path.equals("/chercher.do"))
		{
			String motCle=request.getParameter("motCle");
			SerieModele model= new SerieModele();
			model.setMotCle(motCle);
			List<Serie> series = metier.seriesParMC(motCle);
			model.setSeries(series);
			request.setAttribute("model", model);
			request.getRequestDispatcher("series.jsp").forward(request,response);
		}
		else if (path.equals("/saisie.do") )
		{
			request.getRequestDispatcher("saisieSerie.jsp").forward(request,response);
		}
		else if (path.equals("/save.do") && request.getMethod().equals("POST"))
		{
			String nom=request.getParameter("nom");
			double nbS = Double.parseDouble(request.getParameter("nbS"));
			Serie s = metier.save(new Serie(nom,nbS));
			request.setAttribute("serie", s);
			request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		}
		else if (path.equals("/supprimer.do"))
		{
			Long id= Long.parseLong(request.getParameter("id"));
			metier.deleteSerie(id);
			response.sendRedirect("chercher.do?motCle=");
		}
		else if (path.equals("/editer.do") )
		{
			Long id= Long.parseLong(request.getParameter("id"));
			Serie s = metier.getSerie(id);
			request.setAttribute("serie", s);
			request.getRequestDispatcher("editerSerie.jsp").forward(request,response);
		}
		else if (path.equals("/update.do") )
		{
			Long id = Long.parseLong(request.getParameter("id"));
			String nom=request.getParameter("nom");
			double nbS = Double.parseDouble(request.getParameter("nbS"));
			Serie s = new Serie();
			s.setIdS(id);
			s.setNomS(nom);
			s.setNbS(nbS);
			metier.updateSerie(s);
			request.setAttribute("serie", s);
			request.getRequestDispatcher("confirmatione.jsp").forward(request,response);
		}

		else
		{
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}