package algoII.tp.imple;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.model.AlbumModel;
import algoII.tp.model.FilterModel;
import algoII.tp.utils.HibernateUtils;
import algoII.tp.utils.SaveModel;

public class FilterImpleTrucha implements Filter
{
	private ArrayList<AlbumModel> am;
	
	public FilterImpleTrucha()
	{
		SaveModel sm = new SaveModel();
		this.setAm(sm.deserializeModel().getArray());
	}

	@Override
	public List<Label> getLabels()
	{
		List<Label> lala = null;
		
		for (AlbumModel albumModel : am) {
			for (FilterModel filter : albumModel.getFilters()) {
//				lala.addAll(filter.getFilterName());
			}
		};
		
		return lala;
	}

	public ArrayList<AlbumModel> getAm() {
		return am;
	}

	public void setAm(ArrayList<AlbumModel> am) {
		this.am = am;
	}

}
