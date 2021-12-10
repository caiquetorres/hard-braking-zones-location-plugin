export interface LocationPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
