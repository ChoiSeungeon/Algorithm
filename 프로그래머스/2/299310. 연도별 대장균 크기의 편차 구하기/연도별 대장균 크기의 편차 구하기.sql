-- 코드를 작성해주세요
select year(e.differentiation_date) as YEAR, m.max_size - e.size_of_colony as YEAR_DEV, e.id
from ecoli_data e
join (
select year(differentiation_date) as YEAR, max(size_of_colony) as max_size
from ecoli_data
group by year(differentiation_date)) m
on year(e.differentiation_date) = m.YEAR
order by YEAR, YEAR_DEV;