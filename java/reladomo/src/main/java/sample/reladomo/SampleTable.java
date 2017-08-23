package sample.reladomo;
import java.sql.Timestamp;
public class SampleTable extends SampleTableAbstract
{
	public SampleTable(Timestamp processingDate
	)
	{
		super(processingDate
		);
		// You must not modify this constructor. Mithra calls this internally.
		// You can call this constructor. You can also add new constructors.
	}

	public SampleTable()
	{
		this(sample.reladomo.MyInfinityDateProvider.get());
	}
	
	@Override
	public String toString() {
		return "SampleTable(id=" + this.getId() + ", name=" + this.getName() + ", fromDate=" + this.getProcessingDateFrom() + ", dateTo=" + this.getProcessingDateTo() + ")";
	}
}
