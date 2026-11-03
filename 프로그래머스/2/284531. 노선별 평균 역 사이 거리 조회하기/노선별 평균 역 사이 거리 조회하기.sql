-- 코드를 작성해주세요
select route, concat(round(sum(d_between_dist), 1), 'km') as TOTAL_DISTANCE, 
concat(round(avg(d_between_dist), 2), 'km') as AVERAGE_DISTANCE
from subway_distance
group by route
order by sum(d_between_dist) desc;